import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ModelComponent } from '../model/model.component';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { BankAccountService } from '../services/bank-account.service';
import { BankAccountRequestBody } from '../interfaces/interfaces';

@Component({
  selector: 'app-open-bank-account-form',
  standalone: true,
  imports: [ModelComponent, ReactiveFormsModule],
  templateUrl: './open-bank-account-form.component.html',
  styleUrl: './open-bank-account-form.component.css',
})
export class OpenBankAccountFormComponent {
  constructor(
    private formBuilder: FormBuilder,
    private bankAccountService: BankAccountService
  ) {}
  @Input() formState: boolean = false;
  @Output() onCancelEvent = new EventEmitter<boolean>();
  @Input() customerId: string = '';

  onCancel() {
    this.onCancelEvent.emit(true);
  }
  newAccountForm = this.formBuilder.group({
    accountNumber: ['', Validators.required],
    routingNumber: ['', Validators.required],
    balance: ['', Validators.required],
    type: ['checking', Validators.required],
  });
  onOpenNewAccount() {
    let newAccount: object = {
      ...this.newAccountForm.value,
      accountHolderId: this.customerId,
    };
    this.bankAccountService
      .openNewBankAccount(newAccount as BankAccountRequestBody)
      .subscribe({
        next(value) {
          alert('New account has been created');
          window.location.reload();
        },
        error(error) {
          console.error(error);
          alert('An error has occurred');
        },
      });
  }
}
