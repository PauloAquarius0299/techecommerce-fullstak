/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { Oauth2Service } from './oauth2.service';

describe('Service: Oauth2', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Oauth2Service]
    });
  });

  it('should ...', inject([Oauth2Service], (service: Oauth2Service) => {
    expect(service).toBeTruthy();
  }));
});
