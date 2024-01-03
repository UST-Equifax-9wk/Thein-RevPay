import { Component, OnInit } from '@angular/core';
import { BankAccoutDetail } from '../interfaces/interfaces';
import { CommonModule } from '@angular/common';
import { BankAccountService } from '../services/bank-account.service';
import {
  PRIMARY_OUTLET,
  Router,
  UrlSegment,
  UrlSegmentGroup,
  UrlTree,
} from '@angular/router';
import { SendMoneyFormComponent } from '../send-money-form/send-money-form.component';
import { RequestMoneyFormComponent } from '../request-money-form/request-money-form.component';
import { NavComponent } from '../nav/nav.component';
import { CardsComponent } from '../cards/cards.component';
import { AddCardFormComponent } from '../add-card-form/add-card-form.component';
import { TransactionService } from '../services/transaction.service';
import { ApplyForLoanFormComponent } from '../apply-for-loan-form/apply-for-loan-form.component';
import { LoansComponent } from '../loans/loans.component';

@Component({
  selector: 'app-bank-account',
  standalone: true,
  imports: [
    CommonModule,
    SendMoneyFormComponent,
    RequestMoneyFormComponent,
    NavComponent,
    CardsComponent,
    AddCardFormComponent,
    ApplyForLoanFormComponent,
    LoansComponent,
  ],
  templateUrl: './bank-account.component.html',
  styleUrl: './bank-account.component.css',
})
export class BankAccountComponent implements OnInit {
  constructor(
    private bankAccountService: BankAccountService,
    private router: Router,
    private transactionService: TransactionService
  ) {}
  bankAccountDetail: BankAccoutDetail = {
    accountNumber: '',
    routingNumber: '',
    type: '',
    balance: 0,
    incomingTransactions: [],
    outgoingTransactions: [],
    cards: [],
    loans: [],
  };

  sendMoneyFormState = true;
  onSendMoney(value: boolean) {
    this.sendMoneyFormState = value;
  }

  requestMoneyFormState = true;
  onRequestMoney(value: boolean) {
    this.requestMoneyFormState = value;
  }

  addCardFormState: boolean = true;
  onAddCard(value: boolean) {
    this.addCardFormState = value;
  }

  applyForLoanFormState: boolean = true;
  onApplyForLoan(value: boolean) {
    this.applyForLoanFormState = value;
  }

  ngOnInit(): void {
    let that = this;
    let tree: UrlTree = this.router.parseUrl(this.router.url);
    let group: UrlSegmentGroup = tree.root.children[PRIMARY_OUTLET];
    let segment: UrlSegment[] = group.segments;
    let bankAccountId: string = segment[segment.length - 1].path;
    this.bankAccountService.fetchBankAccount(bankAccountId).subscribe({
      next(value) {
        that.bankAccountDetail = value as BankAccoutDetail;
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }

  onApprove(transactionId: string) {
    this.transactionService.approve(transactionId).subscribe({
      next(response) {
        if (response.status === 200) {
          window.location.reload();
        }
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }

  onDecline(transactionId: string) {
    this.transactionService.decline(transactionId).subscribe({
      next(response) {
        window.location.reload();
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }
}
