import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FeaturedComponent } from '../../featured/featured.component';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink, FeaturedComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
