import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ModelComponent } from '../model/model.component';
import { FormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import { UserReponse } from '../interfaces/interfaces';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customer-search-model',
  standalone: true,
  imports: [ModelComponent, FormsModule, CommonModule],
  templateUrl: './customer-search-model.component.html',
  styleUrl: './customer-search-model.component.css',
})
export class CustomerSearchModelComponent {
  constructor(private userService: UserService) {}
  @Input() modelState: boolean = false;
  @Output() onCancelEvent = new EventEmitter<boolean>();
  onCancel() {
    this.onCancelEvent.emit(true);
  }
  search: string = '';
  customers: any = [];
  onSearch() {
    let that = this;
    this.userService.getUserByUsernameOrEmail(this.search).subscribe({
      next(value) {
        that.customers = value as [];
      },
      error(err) {
        console.error(err);
      },
    });
  }
  @Output() onSelectEvent = new EventEmitter<object>();
  onSelect(customer: object) {
    this.onSelectEvent.emit(customer);
    this.onCancel();
  }
}
