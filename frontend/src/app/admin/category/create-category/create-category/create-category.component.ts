import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { AdminProductService } from '../../../service/admin-product.service';
import { ToastService } from '../../../../shared/toast/toast.service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from "@angular/forms";
import { CreateCategoryFormCategory, ProductCategory } from '../../../model/product.model';
import { injectMutation } from '@tanstack/angular-query-experimental';
import { lastValueFrom } from 'rxjs';
import { NgxControlError } from 'ngxtension/control-error';

@Component({
  selector: 'app-create-category',
  standalone: true,
  imports: [
    CommonModule, 
    ReactiveFormsModule, 
    NgxControlError],
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.scss']
})
export class CreateCategoryComponent {

  formBuilder = inject(FormBuilder);
  productService = inject(AdminProductService);
  toastService = inject(ToastService);
  router = inject(Router);

  name = new FormControl<string>('', { nonNullable: true, validators: [Validators.required] });

  public createForm = this.formBuilder.group<CreateCategoryFormCategory>({
    name: this.name
  });

  loading = false;

  createMutation = injectMutation(() => ({
    mutationFn: 
      (categoryInCreate: ProductCategory) => lastValueFrom(this.productService.createCategory(categoryInCreate)),
    onSettled: () => this.onCreationSettled(),
    onSuccess: () => this.onCreationSuccess(),
    onError: () => this.onCreationError()

  }));

  create(): void {
    const categoryToCreate: ProductCategory = {
      name: this.createForm.getRawValue().name
    };


    this.loading = true;
    this.createMutation.mutate(categoryToCreate);
  }

  private onCreationSettled(): void {
    this.loading = false;
  }

  private onCreationSuccess(): void {
    this.toastService.show("Category created", 'SUCCESS');
    this.router.navigate(['/admin/categories/list']);
  }

  private onCreationError(): void {
    this.toastService.show('Issue when creating category', 'ERROR');
  }
}
