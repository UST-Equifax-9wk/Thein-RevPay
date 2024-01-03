import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { CardResponse } from '../interfaces/interfaces';
import { CardService } from '../services/card.service';

@Component({
  selector: 'app-cards',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cards.component.html',
  styleUrl: './cards.component.css',
})
export class CardsComponent {
  constructor(private cardService: CardService) {}
  @Input()
  cards: CardResponse[] = [];

  onDelete(cardId: string) {
    this.cardService.deleteCard(cardId).subscribe({
      next(value) {
        window.location.reload();
      },
      error(err) {
        alert('An error has occurred');
      },
    });
  }
}
