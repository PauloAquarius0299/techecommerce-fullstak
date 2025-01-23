import { FormControl, FormRecord } from '@angular/forms';

export type ProductSizes = 'XS' | 'S' | 'M' | 'L' | 'XL' | 'XXL'
export const sizes: ProductSizes[] = ['XS', 'S', 'M', 'L', 'XL', 'XXL']

export interface ProductCategory {
  publicId?: string,
  name?: string
}

export interface ProductPictures {
  file: File,
  mimeType: string,
}
export interface BaseProduct {
  brand: string,
  color: string,
  description: string,
  name: string,
  price: number,
  size: ProductSizes,
  category: ProductCategory,
  featured: boolean,
  pictures: ProductPictures[],
  nbInStock: number
}

export interface Product extends BaseProduct{
  publicId: string;
}

export type CreateCategoryFormCategory = {
  name: FormControl<string>;
};

export type CreateProductFormContent = {
  brand: FormControl<string>;
  color: FormControl<string>;
  description: FormControl<string>;
  name: FormControl<string>;
  price: FormControl<number>;
  size: FormControl<ProductSizes>;
  category: FormControl<string>;
  featured: FormControl<boolean>;
  pictures: FormControl<ProductPictures[]>;
  stock: FormControl<number>;
}