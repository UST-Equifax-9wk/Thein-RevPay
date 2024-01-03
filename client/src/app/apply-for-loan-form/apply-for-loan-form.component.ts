import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ModelComponent } from '../model/model.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoanService } from '../services/loan.service';

@Component({
  selector: 'app-apply-for-loan-form',
  standalone: true,
  imports: [ModelComponent, FormsModule, CommonModule],
  templateUrl: './apply-for-loan-form.component.html',
  styleUrl: './apply-for-loan-form.component.css',
})
export class ApplyForLoanFormComponent {
  constructor(private loanService: LoanService) {}
  @Input()
  senderAccount = {};
  @Output()
  onCancelEvent = new EventEmitter<boolean>();

  @Input()
  modelState: boolean = true;
  onCancel() {
    this.onCancelEvent.emit(true);
  }

  amount = 0;
  onApply() {
    let loan = {
      amount: this.amount,
      account: this.senderAccount,
    };
    this.loanService.applyForLoan(loan).subscribe({
      next(value) {
        if ((value as any).status === 200) {
          alert('Loan application success!');
          window.location.reload();
        } else {
          alert("Couldn't complete loan application at this time");
        }
      },
    });
  }
}
