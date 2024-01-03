import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TransactionService } from '../services/transaction.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../services/user.service';
import { EmailService } from '../services/email.service';
import { ModelComponent } from '../model/model.component';
import { AuthService } from '../services/auth.service';
import { EmailDto } from '../interfaces/interfaces';

@Component({
  selector: 'app-request-money-form',
  standalone: true,
  imports: [FormsModule, CommonModule, ModelComponent],
  templateUrl: './request-money-form.component.html',
  styleUrl: './request-money-form.component.css',
})
export class RequestMoneyFormComponent {
  constructor(
    private transactionService: TransactionService,
    private userService: UserService,
    private emailService: EmailService,
    private authService: AuthService
  ) {}

  @Input()
  receiverAccount = {};
  @Output()
  onCancelEvent = new EventEmitter<boolean>();

  @Input()
  formState = false;
  onCancel() {
    this.onCancelEvent.emit(true);
  }

  usernameOrEmail: string = '';
  foundUsers = [
    {
      email: '',
      firstName: '',
      lastName: '',
      id: '',
      username: '',
      bankAccounts: [{ accountNumber: '', type: '' }],
    },
  ];

  selectedAccount = {};
  selectedUser = {};
  onSelectAccount(account: object, user: object) {
    this.selectedAccount = account;
    this.selectedUser = user;
  }

  amount = 0;
  onRequest(senderAccount: object) {
    let transaction = {
      amount: this.amount,
      status: 'pending',
      description: `Requesting money to account number ending in ... ${(
        senderAccount as any
      ).accountNumber.slice(-4)}`,
      receiverAccount: this.receiverAccount,
      senderAccount: senderAccount,
    };
    this.transactionService.requestMoney(transaction).subscribe({
      next(value) {
        alert('A money request has been sent');
        window.location.reload();
      },
      error(error) {
        alert('An error has occurred');
      },
    });
    let email: EmailDto = {
      to: (this.selectedUser as any).email,
      subject: `${this.authService.principal.username} is requesting money`,
      text: `$${transaction.amount} is being requested by ${this.authService.principal.username}. Accept or denie`,
    };
    this.emailService.sendEmail(email).subscribe({
      next(value) {
        console.log(value);
      },
      error(error) {
        console.log(error);
      },
    });
  }

  onSearch() {
    let that = this;
    this.userService.getUserByUsernameOrEmail(this.usernameOrEmail).subscribe({
      next(value) {
        that.foundUsers = value as [];
      },
      error(error) {
        console.log(error);
      },
    });
  }
}
