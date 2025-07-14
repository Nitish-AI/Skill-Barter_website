import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillSearch } from './skill-search';

describe('SkillSearch', () => {
  let component: SkillSearch;
  let fixture: ComponentFixture<SkillSearch>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SkillSearch]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkillSearch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
