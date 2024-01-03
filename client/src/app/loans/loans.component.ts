import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { LoanResponse } from '../interfaces/interfaces';

@Component({
  selector: 'app-loans',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './loans.component.html',
  styleUrl: './loans.component.css',
})
export class LoansComponent {
  @Input()
  loans: LoanResponse[] = [];
}
