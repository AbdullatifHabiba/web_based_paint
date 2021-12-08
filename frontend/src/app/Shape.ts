 export  class Shape {
   public name:string;
  public cx: Number;
  public cy: Number;
  public  color:string;
  public isselcted:boolean;
  public istodraw:boolean;

constructor(){
  this.name='';
  this.color='';
  this.cx=0;
  this.cy=0;
  this.isselcted=false;
  this.istodraw=false;

}
public getname(){return   this.name;}
public setname(name:string){   this.name=name;}
public getcx(){return   this.cx;}
public setcx(cx: Number){   this.cx=cx;}
public getcy(){return   this.cy;}
public setcy(cy: Number){   this.cy=cy;}
public getcolor(){return   this.cx;}
public setcolor(color:string){   this.color=color;}
public getisselected(){return   this.isselcted;}
public setisselected(isselcted:boolean){   this.isselcted=isselcted;}
public getisdraw(){return   this.istodraw;}
public setisdraw(istodraw:boolean){   this.istodraw=istodraw;}


}

