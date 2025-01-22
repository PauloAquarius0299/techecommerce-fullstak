/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { AdminProductService } from './admin-product.service';

describe('Service: AdminProduct', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminProductService]
    });
  });

  it('should ...', inject([AdminProductService], (service: AdminProductService) => {
    expect(service).toBeTruthy();
  }));
});
