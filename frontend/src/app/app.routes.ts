import { Routes } from '@angular/router';
import { roleCheckGuard } from './auth/role-check.guard';
import { AdminCategoriesComponent } from './admin/category/admin-category/admin-categories/admin-categories.component';
import { CreateCategoryComponent } from './admin/category/create-category/create-category/create-category.component';
import { HomeComponent } from './home/home/home/home.component';
<<<<<<< HEAD
=======
import { CreateProductComponent } from './admin/products/create-product/create-product/create-product.component';
import { AdminProductsComponent } from './admin/products/admin-products/admin-products/admin-products.component';
>>>>>>> 7a354c0 (feat: add admin category and products)

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'admin/categories/list',
    component: AdminCategoriesComponent,
    //canActivate: [roleCheckGuard],
   // data: {
     // authorities: ['ROLE_ADMIN'],
    //},
  },
  {
    path: 'admin/categories/create',
    component: CreateCategoryComponent,
    //canActivate: [roleCheckGuard],
    //data: {
     // authorities: ['ROLE_ADMIN'],
    //},
  },
<<<<<<< HEAD
=======
  {
    path: 'admin/products/create',
    component: CreateProductComponent,
  },
  {
    path: 'admin/products/list',
    component: AdminProductsComponent,
  },
>>>>>>> 7a354c0 (feat: add admin category and products)
];
