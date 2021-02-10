import { Component,OnInit, ViewChild} from '@angular/core';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { MatMenuTrigger } from '@angular/material/menu';
import { of as observableOf } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { FlatTreeControl } from '@angular/cdk/tree';
import { CategoryService } from 'app/category/category.service';
import { CategoryNode } from 'app/category/category.model';
import { CatTreeActionComponent } from './dialogs/cattreeaction/cattreeaction.component';
import { JhiAlertService } from 'ng-jhipster';


export interface TreeNode {
  name: string;
  type: string;
  level: number;
  expandable: boolean;
  id: string;
}

@Component({
  selector: 'jhi-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['categories.scss'],
})
export class DashboardCategoriesComponent implements OnInit{
  /** The TreeControl controls the expand/collapse state of tree nodes.  */
  treeControl: FlatTreeControl<TreeNode>;

  /** The TreeFlattener is used to generate the flat list of items from hierarchical data. */
  treeFlattener: MatTreeFlattener<CategoryNode, TreeNode>;

  /** The MatTreeFlatDataSource connects the control and flattener to provide data. */
  dataSource: MatTreeFlatDataSource<CategoryNode, TreeNode>;

  @ViewChild(MatMenuTrigger)
  contextMenu: MatMenuTrigger;

  contextMenuPosition = { x: '0px', y: '0px' };
  contextMenuType = 'category';

  constructor(
    private categoryService: CategoryService,
    private dialog: MatDialog,
    private alertService: JhiAlertService) {
    this.treeFlattener = new MatTreeFlattener(
      this.transformer,
      this.getLevel,
      this.isExpandable,
      this.getChildren);

    this.treeControl = new FlatTreeControl<TreeNode>(this.getLevel, this.isExpandable);
    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
  }
   ngOnInit(): void {
    this.categoryService.getCategoryTree().subscribe(resp => {
       this.dataSource.data = resp;
    })
   }
  /** Transform the data to something the tree can read. */
  transformer(node: CategoryNode, level: number): any {
    return {
      name: node.name,
      type: node.type,
      id: node.id,
      level,
      expandable: !!node.children
    };
  }

 /** Get the level of the node */
  getLevel(node: TreeNode): any {
    return node.level;
  }

  /** Return whether the node is expanded or not. */
  isExpandable(node: TreeNode): any {
    return node.expandable;
  };

  /** Get the children for the node. */
  getChildren(node: CategoryNode): any {
    return observableOf(node.children);
  }

  /** Get whether the node has children or not. */
  hasChild(index: number, node: TreeNode): any{
    return node.expandable;
  }
  getParentId(id: string): string{
    let parentId = null;
    this.dataSource.data.forEach((cNode: CategoryNode) => {
        if(cNode.children){
          cNode.children.forEach(sc => {
            if(sc.id === id){
              parentId = cNode.id;
            }
          })
      }
    })
    return parentId;
  }
  getCategoryNode(id: string): CategoryNode{
    let categoryNode = null;
    this.dataSource.data.forEach((cNode: CategoryNode) => {
        if(cNode.id === id){
          categoryNode = cNode;
        } else if(cNode.children){
          cNode.children.forEach(sc => {
            if(sc.id === id){
              categoryNode = cNode;
            }
          })
        }
    })
    return categoryNode;
  }
  onContextItemSelect(actionId: any): void {
     let parentIndex = null;
     const node = this.contextMenu.menuData.node;
     if(actionId==='renameCategory' || actionId==='renameSubCategory' || actionId ==='createSubCategory'){
        let newName = '';
        if(actionId !=='createSubCategory'){
          newName = node.name;
        }
        let parentId = '';
        if(actionId==='renameSubCategory'){
          parentId = this.getParentId(node.id);
        } else if (actionId ==='createSubCategory'){
          parentId = node.id;
        }
        this.treeControl.dataNodes.forEach((n,i) => {
          if(n.id === parentId){
            parentIndex = i;
          }
        })
      this.handleAction(actionId,node,newName, parentIndex);
     } else if(actionId === 'deleteCategory'){
      this.deleteCategory(node);
     } else if(actionId ==='deleteSubCategory'){
      this.deleteSubCategory(node);
     }
  }
  openContextMenu(event: MouseEvent, node: any): void{
    event.preventDefault();
    this.contextMenuType = node.type;
    this.contextMenuPosition.x = event.clientX + 'px';
    this.contextMenuPosition.y = event.clientY + 'px';
    this.contextMenu.menuData = { node };
    this.contextMenu.openMenu();
  }
 addCateogry(): void {
    this.handleAction('createCategory',null,'',null);
 }
 deleteSubCategory(node: any): void {
    const parentNode = this.getCategoryNode(node.id);
    this.categoryService.deleteSubCategory(node.id).subscribe(resp => {
     const nodes = this.dataSource.data.filter(item => item.id !== parentNode.id);
      const children = parentNode.children.filter(item => item.id !== node.id);
      parentNode.children = children;
      nodes.push(parentNode);
      this.dataSource.data = nodes;
    });
 }
 deleteCategory(node: any): void {
     const categoryNode = this.getCategoryNode(node.id);
     let error = '';
    if(categoryNode.children && categoryNode.children.length > 0 ) {
      error = 'categoryIsNotEmpty';
    }
    if(!error){
      this.categoryService.deleteCategory(node.id).subscribe(resp => {
        const nodes = this.dataSource.data.filter(item => item.id !== node.id);
        this.dataSource.data = nodes;
      });
    } else {
      this.alertService.addAlert({ type: 'warning', msg: error, timeout: 5000, toast: true }, []);
    }
 }
 handleAction(actionId: string, node: any, newName: string, parentIndex: number): void {
    const data = {
     actionId,
     node,
     nodes: this.dataSource.data,
     newName
    }
    const dialogRef = this.dialog.open(CatTreeActionComponent, { width: '287px', data, autoFocus: true});
    dialogRef.afterClosed().subscribe(nodes => {
       if(nodes){
         this.dataSource.data = nodes;
         if(parentIndex !== null){
          this.treeControl.expand(this.treeControl.dataNodes[parentIndex]);
         }

       }
    });
 }
}
