import { Component, OnInit } from '@angular/core';
import { NavComponent } from '../nav/nav.component';
import { CustomerSearchModelComponent } from '../customer-search-model/customer-search-model.component';
import { CommonModule, JsonPipe } from '@angular/common';
import { CustomerComponent } from '../customer/customer.component';
import { AdminService } from '../services/admin.service';
import { AdminResponse, LoanResponse } from '../interfaces/interfaces';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    NavComponent,
    CustomerSearchModelComponent,
    JsonPipe,
    CommonModule,
    CustomerComponent,
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css',
})
export class AdminComponent implements OnInit {
  constructor(private adminService: AdminService) {}
  searchState: boolean = true;
  onSearch(value: boolean) {
    this.searchState = value;
  }
  selectedCustomer: object = {};
  onSelect(value: object) {
    this.selectedCustomer = value;
  }

  loans: LoanResponse[] = [];
  ngOnInit(): void {
    let that = this;
    this.adminService.getAdminByUsername('th').subscribe({
      next(value) {
        that.loans = (value as AdminResponse).loans;
      },
    });
  }

  onApprove(loan: LoanResponse) {
    this.adminService.approveLoan(loan).subscribe({
      next(value) {
        alert('The loan has been approved');
        window.location.reload();
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }
}
