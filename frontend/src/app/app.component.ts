import { Oauth2Service } from './auth/oauth2.service';
import { Component, inject, OnInit, PLATFORM_ID } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FaConfig, FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { fontAwesomeIcons } from './shared/font-awesome-icons';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { isPlatformBrowser } from '@angular/common';
import { throws } from 'assert';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, 
    FontAwesomeModule,
    NavbarComponent,
    FooterComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'frontend';

  private faIconLibrary = inject(FaIconLibrary);
  private faConfig = inject(FaConfig);

  private Oauth2Service = inject(Oauth2Service);

  platformId = inject(PLATFORM_ID);

  constructor() {
    if(isPlatformBrowser(this.platformId)){
      this.Oauth2Service.initAuthentication();
    }
    this.Oauth2Service.connectedUserQuery = this.Oauth2Service.fetch();
  }

  ngOnInit(): void {
    this.initFontAwesome();
  }

  private initFontAwesome(){
    this.faConfig.defaultPrefix = 'fas';
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }
  
}
