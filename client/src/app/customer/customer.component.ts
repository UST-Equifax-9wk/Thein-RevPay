import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { BankAccoutDetail } from '../interfaces/interfaces';
import { OpenBankAccountFormComponent } from '../open-bank-account-form/open-bank-account-form.component';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, OpenBankAccountFormComponent],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css',
})
export class CustomerComponent {
  @Input() customer: any = {};
  selectedAccount: BankAccoutDetail = {
    accountNumber: '',
    routingNumber: '',
    type: '',
    balance: 0,
    incomingTransactions: [],
    outgoingTransactions: [],
    cards: [],
    loans: [],
  };
  onViewDetails(account: BankAccoutDetail) {
    this.selectedAccount = account;
  }
  openNewBankAccountFormState: boolean = true;
  onOpenNewBankAccount(value: boolean) {
    this.openNewBankAccountFormState = value;
  }
  onOpenNewBankAccountCancel(value: boolean) {
    this.openNewBankAccountFormState = value;
  }
}
