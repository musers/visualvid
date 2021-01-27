export interface CategoryNode {
  name: string;
  type?: string;
  id? : string;
  children?: CategoryNode[];
}

export interface Category {
  categoryId: String;
  categoryName: String;
  id?: string;
  nameFormatted?: string;
}

export interface SubCategory {
  categoryId?: String;
  categoryName?: String;
  id?: string;
  name?: string;
}
