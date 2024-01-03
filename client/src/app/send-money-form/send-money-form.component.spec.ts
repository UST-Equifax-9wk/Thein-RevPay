import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendMoneyFormComponent } from './send-money-form.component';

describe('SendMoneyFormComponent', () => {
  let component: SendMoneyFormComponent;
  let fixture: ComponentFixture<SendMoneyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SendMoneyFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SendMoneyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
