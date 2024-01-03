import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestMoneyFormComponent } from './request-money-form.component';

describe('RequestMoneyFormComponent', () => {
  let component: RequestMoneyFormComponent;
  let fixture: ComponentFixture<RequestMoneyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestMoneyFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RequestMoneyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
