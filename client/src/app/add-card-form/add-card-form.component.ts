import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Card } from '../interfaces/interfaces';
import { CardService } from '../services/card.service';
import { ModelComponent } from '../model/model.component';
import {
  PRIMARY_OUTLET,
  Router,
  UrlSegment,
  UrlSegmentGroup,
  UrlTree,
} from '@angular/router';

@Component({
  selector: 'app-add-card-form',
  standalone: true,
  imports: [ReactiveFormsModule, ModelComponent],
  templateUrl: './add-card-form.component.html',
})
export class AddCardFormComponent {
  constructor(
    private formBuilder: FormBuilder,
    private cardService: CardService,
    private router: Router
  ) {}

  form = this.formBuilder.group({
    nameOnCard: ['', Validators.required],
    cardNumber: ['', Validators.required],
    svc: ['', Validators.required],
    expirationDate: ['', Validators.required],
  });

  onSubmit() {
    let tree: UrlTree = this.router.parseUrl(this.router.url);
    let group: UrlSegmentGroup = tree.root.children[PRIMARY_OUTLET];
    let segment: UrlSegment[] = group.segments;
    let bankAccountId: string = segment[segment.length - 1].path;
    let card: Card = {
      ...(this.form.value as Card),
      bankAccount: {
        id: bankAccountId,
      },
    };
    this.cardService.addCard(card).subscribe({
      next(response) {
        console.log(response);
        if (response.status === 200 && response.body) {
          window.location.reload();
          alert('New card has been added');
        }
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }

  @Input()
  modelState: boolean = true;
  @Output()
  onCancelEvent = new EventEmitter<boolean>();

  onCancel() {
    this.onCancelEvent.emit(true);
  }
}
