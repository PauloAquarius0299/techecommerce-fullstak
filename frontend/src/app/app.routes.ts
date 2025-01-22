import { Routes } from '@angular/router';
import { roleCheckGuard } from './auth/role-check.guard';
import { AdminCategoriesComponent } from './admin/category/admin-category/admin-categories/admin-categories.component';
import { CreateCategoryComponent } from './admin/category/create-category/create-category/create-category.component';
import { HomeComponent } from './home/home/home/home.component';

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
];
