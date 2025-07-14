import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileSkills } from './profile-skills';

describe('ProfileSkills', () => {
  let component: ProfileSkills;
  let fixture: ComponentFixture<ProfileSkills>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileSkills]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileSkills);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
