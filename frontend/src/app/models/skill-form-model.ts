export interface SkillFormModel {
    title: string;
    description: string;
    category: string;
    tags: string[];
    intent: 'teach' | 'learn';
    skillLevel: string;
    prerequisites: string;
    equipment: string;
    sessionFormat: string;
}

