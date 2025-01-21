import { ApplicationConfig, EnvironmentInjector, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AbstractSecurityStorage, authInterceptor, LogLevel, provideAuth } from "angular-auth-oidc-client";
import { environment } from '../environments/environment';
import { provideQueryClient, QueryClient } from "@tanstack/angular-query-experimental";
import { SsrStorageServiceService } from './auth/ssr-storage-service.service';


export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes, withComponentInputBinding()),
    provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch(), withInterceptors([authInterceptor()])),
    provideAuth({
      config: {
        authority: environment.kinde.authority,
        redirectUrl: environment.kinde.redirectUrl,
        postLogoutRedirectUri: environment.kinde.postLogoutRedirectUri,
        clientId: environment.kinde.clientId,
        scope: 'openid profile email offline',
        responseType: 'code',
        silentRenew: true,
        useRefreshToken: true,
        logLevel: LogLevel.Warn,
        secureRoutes: [environment.apiUrl],
        customParamsAuthRequest: {
          audience: environment.kinde.audience,
        },
      },
    }),
    {provide: AbstractSecurityStorage, useClass: SsrStorageServiceService},
    provideQueryClient(new QueryClient)
  ]
};
