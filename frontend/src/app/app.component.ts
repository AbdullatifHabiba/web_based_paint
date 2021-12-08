import { Shape } from './Shape';
import { Component, ElementRef, ViewChild,OnInit ,HostListener,Directive, AfterViewInit} from '@angular/core';
import { HttpClient ,HttpErrorResponse,HttpParams} from '@angular/common/http';
    import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',

  styleUrls: ['./app.component.css']
})
export class AppComponent  implements AfterViewInit{
  title = 'webpaint';
  constructor(private httpClient:HttpClient){

  }

  @ViewChild('canvas',{static:true})
  mycanvas!: ElementRef<HTMLCanvasElement>;

  public Square!: CanvasRenderingContext2D;


  color='';

ngAfterViewInit(): void {this.Square=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
this.mycanvas.nativeElement.width=window.innerWidth;
this.mycanvas.nativeElement.height=window.innerHeight;

}
   startx=0;
   starty=0;

      selectedshape:String='';
      private paint: boolean = false;
      private clickX: number[] = [];
      private clickY: number[] = [];
      private clickDrag: boolean[] = [];




    private pressEventHandler = (e: MouseEvent ) => {
      let mouseX =  (e as MouseEvent).pageX;
      let mouseY = (e as MouseEvent).pageY;
      var rect = this.mycanvas.nativeElement.getBoundingClientRect();
      mouseX -=rect.left;
      mouseY -=rect.top;
      this.paint = true;
      this.addClick(mouseX, mouseY, false);

    }

    private dragEventHandler = (e: MouseEvent ) => {
      let mouseX = (e as MouseEvent).pageX;
      let mouseY =(e as MouseEvent).pageY;
     var rect = this.mycanvas.nativeElement.getBoundingClientRect();

      mouseX -=rect.left;
       mouseY -=rect.top;


      if (this.paint) {

          this.addClick(mouseX, mouseY, true);
          this.Square.clearRect(0,0,this.mycanvas.nativeElement.width,this.mycanvas.nativeElement.height);

          this.redraw(this.selectedshape);

          this.shapeslist.pop();
          this.canvasships.pop();
      }

      e.preventDefault();
    }
    private addClick(x: number, y: number, dragging: boolean) {
      this.clickX.push(x);
      this.clickY.push(y);
      this.clickDrag.push(dragging);
    }

    public clearCanvas() {
      this.mycanvas.nativeElement.width = this.mycanvas.nativeElement.width;

      this.Square.clearRect(0,0,this.mycanvas.nativeElement.width,this.mycanvas.nativeElement.height);
      this.canvasships=[];
      this.shapeslist=[];

      this.clickX = [];
      this.clickY = [];
      this.clickDrag = [];
    }
    private clearEventHandler = () => {
      this.clickX = [];
      this.clickY = [];
      this.clickDrag = [];
    }

    private releaseEventHandler = () => {
      this.paint = false;
      this.redraw(this.selectedshape);
      this.Square.save()

      this.clearEventHandler();
    }

    private cancelEventHandler = () => {
      this.paint = false;
    }
    private createUserEvents() {
      let canvas = this.mycanvas.nativeElement;

      canvas.addEventListener("mousedown", this.pressEventHandler);
      canvas.addEventListener("mousemove", this.dragEventHandler);
      canvas.addEventListener("mouseup", this.releaseEventHandler);
      canvas.addEventListener("mouseout", this.cancelEventHandler);




    }

    private redraw(shapename:String) {
      this.createUserEvents();
     let s1=new Path2D; let s2=new Path2D; let s3=new Path2D; let s4=new Path2D; let s5=new Path2D;

      let clickDrag = this.clickDrag;
      this.startx=this.clickX[0];
      this.starty=this.clickY[0];
      let width=this.clickX[0]-this.clickX[this.clickX.length-1]
      let height=this.clickY[0]-this.clickY[this.clickY.length-1]
      if(width<0){width*=-1;}
      if(height<0){height*=-1;}

      switch(shapename){
        case "circle":
         this.putattr(this.cr,'circle',this.startx,this.starty,width,height,this.color,false,true)

         s1.arc(this.startx,this.starty,width,0,Math.PI*2)
         this.Square.stroke(s1);
         this.Square.strokeStyle=this.color;

         this.shapeslist.push(this.cr);
         this.canvasships.push(this.Square)
        break;
        case "elipse":
         this.putattr(this.el,'elipse',this.startx,this.starty,width,height, this.color,false,true)

         s2.ellipse(this.startx,this.starty,width,width,0,0,Math.PI*2)
        this.Square.stroke(s2);
        this.Square.strokeStyle=this.color;

        this.shapeslist.push(this.el);
         this.canvasships.push(this.Square)
        break;
      case "rectangle":
       this.putattr(this.re,'rectangle',this.startx,this.starty,width,height ,this.color,false,true)

       s3.rect(this.startx,this.starty,width,height)
       this.Square.stroke(s3);
       this.Square.strokeStyle=this.color;

       this.shapeslist.push(this.re);
       this.canvasships.push(this.Square)
      break;

      case "line":
       this.putattr(this.li,'line',this.startx,this.starty,width,height, this.color,false,true)

        s4.moveTo(this.startx,this.starty)
        s4.lineTo(this.clickX[this.clickX.length-1],this.clickY[this.clickY.length-1]);
        this.Square.stroke(s4);
        this.Square.strokeStyle=this.color;

        this.shapeslist.push(this.li);
        this.canvasships.push(this.Square)
         break;


      case "triangle":
        this.putattr(this.tr,'triangle',this.startx,this.starty,width,height,this.color,false,true)
        this.Square.strokeStyle = this.color;
        s5.moveTo(this.startx, this.starty);
        s5.lineTo(this.clickX[this.clickX.length-1],this.clickY[this.clickY.length-1]);
        s5.lineTo(150,100);
        s5.closePath()
        this.Square.stroke(s5);
        this.shapeslist.push(this.tr);
       this.canvasships.push(this.Square)
        break;




   }
    }
    UNDO(){

    }
    REDO(){

    }
    Save(){this.mycanvas.nativeElement.toDataURL}
shapeslist=new Array()
canvasships=new Array()

cr=new Shape();
li=new Shape();
tr=new Shape();
re=new Shape();
el=new Shape();

 Draw(shape:String){
this.selectedshape=shape;
switch(shape){
     case "circle":
     this.redraw("circle");
     break;
     case "elipse":
      this.redraw("elipse");

     break;
   case "rectangle":
    this.redraw("rectangle");

    break;

   case "line":
    this.redraw("line");

      break;

   case "triangle":
    this.redraw("triangle");


     break;




}


}

putattr(sh:Shape,name:string,x:Number,y:Number,w:Number,h:Number, color:string,sel:boolean,draw:boolean){
  sh.setname(name)
  sh.setcolor(color)
  sh.setcx(x) ;
  sh.setcy(y);
  sh.setwidth(w) ;
  sh.setheight(h);
  sh.setisdraw(sel);
  sh.setisdraw(draw)
  return sh;
}


results:any;
send(){
  const serverUrl = environment.serverUrl;
  let params = new HttpParams();

    params = params.append('sentobj',this.shapeslist[this.shapeslist.length-1])

      this.httpClient.get(serverUrl,{
      params:params,
      observe:'response'
    }
      ).subscribe(
    (response)=>{this.results=response.body;
    },
    (error:HttpErrorResponse)=>{alert(error.message)}

        );
  }

}





