import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyForLoanFormComponent } from './apply-for-loan-form.component';

describe('ApplyForLoanFormComponent', () => {
  let component: ApplyForLoanFormComponent;
  let fixture: ComponentFixture<ApplyForLoanFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplyForLoanFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyForLoanFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
