import { Component, effect, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminProductService } from '../../../service/admin-product.service';
import { ToastService } from '../../../../shared/toast/toast.service';
import { injectMutation, injectQuery, injectQueryClient } from '@tanstack/angular-query-experimental';
import { lastValueFrom } from 'rxjs';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-categories',
  imports: [RouterModule, FontAwesomeModule, CommonModule],
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.scss']
})
export class AdminCategoriesComponent  {

  productAdminService = inject(AdminProductService);

  toastService = inject(ToastService);
  queryClient = injectQueryClient();

  constructor() {
    effect(() => this.handleCategoriesQueryError());
  }

  categoriesQuery = injectQuery(() => ({
    queryKey: ['categories'],
    queryFn: () => lastValueFrom(this.productAdminService.findAllCategories()),
  }));

  deleteMutation = injectMutation(() => ({
    mutationFn: (categoriesPublicId: string) => 
      lastValueFrom(this.productAdminService.deleteCategory(categoriesPublicId)),
    onSuccess: () => this.onDeletionSuccess(),
    onError: () => this.onDeletionError(),
  }));

  private onDeletionSuccess(): void {
    this.queryClient.invalidateQueries({ queryKey: ['categories'] });
    this.toastService.show('Categories deleted', 'SUCCESS');
  }

  private onDeletionError(): void {
    this.toastService.show('Issue when deleting category', 'ERROR');
  }

  private handleCategoriesQueryError(){
    if(this.categoriesQuery.isError()){
      this.toastService.show(
        'Error! Failed to load categories. please tru again',
        'ERROR'
      );
    }
  }

  deleteCategory(publicId?: string) {
    if (publicId) {
      this.deleteMutation.mutate(publicId);
    }
  }

}
