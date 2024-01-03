import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import { TransactionService } from '../services/transaction.service';
import { ModelComponent } from '../model/model.component';
import { AuthService } from '../services/auth.service';
import { EmailService } from '../services/email.service';
import { EmailDto } from '../interfaces/interfaces';

@Component({
  selector: 'app-send-money-form',
  standalone: true,
  imports: [CommonModule, FormsModule, ModelComponent],
  templateUrl: './send-money-form.component.html',
  styleUrl: './send-money-form.component.css',
})
export class SendMoneyFormComponent {
  constructor(
    private userService: UserService,
    private transactionService: TransactionService,
    private authService: AuthService,
    private emailService: EmailService
  ) {}
  @Output()
  onCancelEvent = new EventEmitter<boolean>();

  @Input()
  senderAccount = {};

  @Input()
  formState = false;

  usernameOrEmail: string = '';
  onCancel() {
    this.onCancelEvent.emit(true);
  }

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

  amount = 0;
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

  selectedUser = {};
  onSelectAccount(account: object, user: object) {
    this.selectedAccount = account;
    this.selectedUser = user;
  }

  onSend(receiverAccount: object) {
    let transaction = {
      amount: this.amount,
      status: 'pending',
      description: `sending money to account number ending in ... ${(
        receiverAccount as any
      ).accountNumber.slice(-4)}`,
      senderAccount: this.senderAccount,
      receiverAccount: receiverAccount,
    };
    this.transactionService.sendMoney(transaction).subscribe({
      next(value) {
        alert('Sending money completed');
        window.location.reload();
      },
      error(error) {
        alert('An error has occurred');
      },
    });

    let emailForSender: EmailDto = {
      to: this.authService.principal.email,
      subject: `$${this.amount} was sent to ${
        (this.selectedUser as any).email
      }`,
      text: `$${this.amount} was sent to ${
        (this.selectedUser as any).username
      }`,
    };

    this.emailService.sendEmail(emailForSender).subscribe({
      next(value) {
        console.log(value);
      },
      error(err) {
        console.error(err);
      },
    });

    let emailForReceiver: EmailDto = {
      to: (this.selectedUser as any).email,
      subject: `${this.authService.principal.username} sent you $${this.amount}`,
      text: `${this.authService.principal.username} sent you $${this.amount}`,
    };
    this.emailService.sendEmail(emailForReceiver).subscribe({
      next(value) {
        console.log(value);
      },
      error(err) {
        console.log(err);
      },
    });
  }
}
