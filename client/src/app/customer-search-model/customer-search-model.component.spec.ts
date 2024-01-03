import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerSearchModelComponent } from './customer-search-model.component';

describe('CustomerSearchModelComponent', () => {
  let component: CustomerSearchModelComponent;
  let fixture: ComponentFixture<CustomerSearchModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerSearchModelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomerSearchModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
