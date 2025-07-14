import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Login } from './pages/login/login';
import { Signup } from './pages/signup/signup';
import { Profile } from './pages/profile/profile';
import { SkillForm } from './pages/skill-form/skill-form';
import { SkillsITeach } from './pages/skills-i-teach/skills-i-teach';
import { SkillsIWant } from './pages/skills-i-want/skills-i-want';
import { Matches } from './pages/matches/matches';
import { Schedule } from './pages/schedule/schedule';
import { Reviews } from './pages/reviews/reviews';


export const routes: Routes = [
    { path: '', component: Home },
    { path: 'home', component: Home },
    { path: 'login', component: Login },
    { path: 'signup', component: Signup },
    { path: 'profile', component: Profile },
    { path: 'add-skill', component: SkillForm },
    { path: 'edit-skill/:id', component: SkillForm },
    { path: 'teach', component: SkillsITeach },
    { path: 'learn', component: SkillsIWant },
    { path: 'matches', component: Matches },
    { path: 'schedule', component: Schedule },
    { path: 'reviews', component: Reviews }


];
