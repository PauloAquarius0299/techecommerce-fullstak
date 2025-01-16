import { Oauth2Service } from './../../auth/oauth2.service';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, FontAwesomeModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent  {

  Oauth2Service = inject(Oauth2Service);

  connectedUserQuery = this.Oauth2Service.connectedUserQuery;

  login(): void {
    this.closeDropDownMenu();
    this.Oauth2Service.login();
  }

  logout(): void {
    this.closeDropDownMenu();
    this.Oauth2Service.logout();
  }

  isConnected(): boolean {
    return this.connectedUserQuery?.status() === 'success'
    && this.connectedUserQuery?.data()?.email !== this.Oauth2Service.notConnected
  }
  closeDropDownMenu() {
    const bodyElement = document.activeElement as HTMLBodyElement;
    if(bodyElement){
      bodyElement.blur();
    }
  }

  constructor() { }


}
