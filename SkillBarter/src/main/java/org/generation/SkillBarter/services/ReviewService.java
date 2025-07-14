package org.generation.SkillBarter.services;

import org.generation.SkillBarter.dto.ReviewDTO;
import org.generation.SkillBarter.enums.ScheduledStatus;
import org.generation.SkillBarter.model.Review;
import org.generation.SkillBarter.model.ScheduledSession;
import org.generation.SkillBarter.model.User;
import org.generation.SkillBarter.repositories.ReviewRepository;
import org.generation.SkillBarter.repositories.ScheduledSessionRepository;
import org.generation.SkillBarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ScheduledSessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public Review submitReview(ReviewDTO dto) {
        if (reviewRepository.existsBySessionId(dto.getSessionId())) {
            throw new RuntimeException("Review for this session already exists.");
        }

        ScheduledSession session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (session.getStatus() != ScheduledStatus.COMPLETED) {
            throw new RuntimeException("Cannot review an incomplete session.");
        }

        User reviewer = userRepository.findById(dto.getReviewerId())
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));

        User reviewedUser = session.getSessionRequest().getReceiver().getId().equals(dto.getReviewerId())
                ? session.getSessionRequest().getRequester()
                : session.getSessionRequest().getReceiver();

        Review review = new Review();
        review.setReviewer(reviewer);
        review.setReviewedUser(reviewedUser);
        review.setSession(session);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForUser(Long userId) {
        return reviewRepository.findByReviewedUserId(userId);
    }
}
