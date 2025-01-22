/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SsrStorageServiceService } from './ssr-storage-service.service';

describe('Service: SsrStorageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SsrStorageServiceService]
    });
  });

  it('should ...', inject([SsrStorageServiceService], (service: SsrStorageServiceService) => {
    expect(service).toBeTruthy();
  }));
});
