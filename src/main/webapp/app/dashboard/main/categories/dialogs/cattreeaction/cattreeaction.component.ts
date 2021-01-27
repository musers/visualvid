import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { CategoryService } from 'app/category/category.service';
import { CategoryNode } from 'app/category/category.model';

export interface CatTreeActionConfig {
    node?: any,
    nodes?: Array<CategoryNode>;
    actionId?: string,
    newName?: string
}

@Component({
  selector: 'jhi-cattree-action-popup',
  templateUrl: './cattreeaction.component.html'
})
export class CatTreeActionComponent {
  get dialog(): any {
      return this.data;
  }
  error = '';
  constructor(
      @Inject(MAT_DIALOG_DATA) public data: CatTreeActionConfig,
      public dialogRef: MatDialogRef<CatTreeActionConfig>,
      private categoryService: CategoryService
  ) {}

  rename(type: string): void{
    this.error = '';
    if(!this.data.newName){
      this.error = 'Name cannot be empty';
    } else if(type==='category' && this.categoryExists(this.data.newName,this.data.node.id)){
      this.error = 'Category already exists with the same name';
    } else if(type==='subCategory' && this.checkSubCategoryExists(this.data.newName,this.data.node.id)){
      this.error = 'Sub Category with the same name exists';
    }

    if(!this.error){
      this.categoryService.rename(this.data.node.id,this.data.node.type,this.data.newName).subscribe(resp => {
        console.log(resp);
          this.data.nodes.forEach(cNode => {
            if(cNode.id === this.data.node.id){
              cNode.name = this.data.newName;
            }
            if(cNode.children){
              cNode.children.forEach(sc => {
                if(sc.id === this.data.node.id){
                  sc.name = this.data.newName;
                }
              })
            }
          })
        this.dialogRef.close(this.data.nodes);
      })
    }
  }
  createSubCategory(): void {
    this.error = '';
    if(!this.data.newName){
      this.error = 'Name cannot be empty';
    } else if(this.checkSubCategoryExists(this.data.newName, null)){
       this.error = 'Sub Category already exists in the same category';
    } else {
      this.error = '';
    }
    if(!this.error){
      this.categoryService.createSubCategory(this.data.node.id,this.data.newName).subscribe(resp => {
          const n = {
            name: this.data.newName,
            type: 'subCategory',
            id: resp.id,
            expandable: false,
            level: 1
          }
          this.data.nodes.forEach(cNode => {
            if(cNode.id === this.data.node.id){
              if(!cNode.children){
                cNode.children = [];
              }
              cNode.children.push(n)
            }
          })

       this.dialogRef.close(this.data.nodes);
      });
    }
  }
  createCategory(): void {
    this.error = '';
    console.log('createCategory');
    if(!this.data.newName){
      this.error = 'Name cannot be empty '
    } else if(this.categoryExists(this.data.newName, null)){
      this.error = 'Category already exists with name : '+ this.data.newName;
    } else {
      this.error = '';
    }
    if(!this.error){
      this.categoryService.createCategory(this.data.newName).subscribe(resp => {
        const n = {
            name: this.data.newName,
            type: 'category',
            id: resp.id,
            expandable: true,
            level: 0
        }
        this.data.nodes.push(n);
        this.dialogRef.close(this.data.nodes);
       })
    }
  }
  categoryExists(name: string, id: string): boolean{
    let cExists = false;
    this.data.nodes.forEach(cNode => {
      if(cNode.name.toLocaleLowerCase() === name.toLocaleLowerCase()){
        if(cNode.id !== id){
          cExists =  true;
        }
      }
    })
    return cExists;
  }
  checkSubCategoryExists(name: string, id: string): boolean {
    let scExists =false;
      if(!id){
          this.data.nodes.forEach(cNode => {
            if(cNode.id === this.data.node.id && cNode.children){
              cNode.children.forEach(sc => {
                if(sc.name === name){
                    scExists = true;
                }
              })
            }
          })
      } else {
          this.data.nodes.forEach(cNode => {
            if(cNode.children){
              cNode.children.forEach(sc => {
                if(sc.name === name && sc.id !== id){
                    scExists = true;
                }
              })
            }
          })
      }
    return scExists;
  }
}
