import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenBankAccountFormComponent } from './open-bank-account-form.component';

describe('OpenBankAccountFormComponent', () => {
  let component: OpenBankAccountFormComponent;
  let fixture: ComponentFixture<OpenBankAccountFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OpenBankAccountFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OpenBankAccountFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
