export interface UserProfile {
    firstName: string;
    lastName: string;
    userName: string;
    bio: string;
    address: string;
    dateOfJoin: string; // keep as string if it's ISO format from backend
    imageUrl: string;
}
