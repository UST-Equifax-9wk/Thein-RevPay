import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { NavComponent } from '../nav/nav.component';
import { CommonModule } from '@angular/common';
import { BankAccountService } from '../services/bank-account.service';
import { BankAccount } from '../interfaces/interfaces';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  constructor(
    private bankAccountService: BankAccountService,
    private router: Router
  ) {}
  bankAccounts: BankAccount[] = [];

  accountTypes = ['saving', 'checking', 'business'];
  checkingAccounts: BankAccount[] = [];
  savingAccounts: BankAccount[] = [];
  businessAccounts: BankAccount[] = [];
  totalBalance: number = 0;

  ngOnInit(): void {
    let that = this;
    this.bankAccountService.fetchBankAccounts().subscribe({
      next(bankAccounts) {
        that.bankAccounts = bankAccounts as BankAccount[];
        for (let bankAccount of bankAccounts as BankAccount[]) {
          if (bankAccount.type === 'saving') {
            that.savingAccounts.push(bankAccount);
          } else if (bankAccount.type === 'checking') {
            that.checkingAccounts.push(bankAccount);
          } else {
            that.businessAccounts.push(bankAccount);
          }
          that.totalBalance += bankAccount.balance;
        }
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }

  viewDetail(id: string) {
    this.router.navigate(['bank-accounts', id]);
  }
}
