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
  constructor(private httpClient:HttpClient){}

  @ViewChild('canvas',{static:true})
  mycanvas!: ElementRef<HTMLCanvasElement>;
  public circle!: CanvasRenderingContext2D;
  public line!: CanvasRenderingContext2D;
  public rectangle!: CanvasRenderingContext2D;
  public triangle!: CanvasRenderingContext2D;
  public elipse!: CanvasRenderingContext2D;
  public Square!: CanvasRenderingContext2D;


  color='';
ngAfterViewInit(): void {
  this.circle=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
  this.line=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
  this.rectangle=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
  this.elipse=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
  this.Square=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
  this.triangle=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");
  this.redraw();
  this.createUserEvents();

}
 startx=0;
   starty=0;


      private paint: boolean = false;
      private clickX: number[] = [];
      private clickY: number[] = [];
      private clickDrag: boolean[] = [];




    private pressEventHandler = (e: MouseEvent ) => {
      let mouseX =  (e as MouseEvent).pageX;
      let mouseY = (e as MouseEvent).pageY;
      var rect = this.mycanvas.nativeElement.getBoundingClientRect();

      mouseX -= rect.left;
       mouseY -= rect.top;

      this.paint = true;
      console.log(mouseX,mouseY)
      this.addClick(mouseX, mouseY, false);
      this.redraw();
    }

    private dragEventHandler = (e: MouseEvent ) => {
      let mouseX = (e as MouseEvent).pageX;
      let mouseY =(e as MouseEvent).pageY;
     var rect = this.mycanvas.nativeElement.getBoundingClientRect();

      mouseX -= rect.left;
       mouseY -= rect.top;


      if (this.paint) {
          this.addClick(mouseX, mouseY, true);
          this.redraw();
      }

      e.preventDefault();
    }
    private addClick(x: number, y: number, dragging: boolean) {
      this.clickX.push(x);
      this.clickY.push(y);
      this.clickDrag.push(dragging);
    }

    public clearCanvas() {
      this.Square.clearRect(0, 0, this.mycanvas.nativeElement.width, this.mycanvas.nativeElement.height);
      this.clickX = [];
      this.clickY = [];
      this.clickDrag = [];
    }
    private clearEventHandler = () => {
      this.clearCanvas();
    }

    private releaseEventHandler = () => {
      this.paint = false;
      this.redraw();
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

    private redraw() {
      let clickX = this.clickX;
      let context = this.Square;
      let clickDrag = this.clickDrag;
      let clickY = this.clickY;
      let x=0,y=0;
      for (let i = 0; i < clickX.length; ++i) {
          if (clickDrag[i] && i) {
            context.rect(clickX[x], clickY[x],clickX[i]-clickX[x],clickY[i]-clickY[x]);

          } else {
            context.rect(clickX[x], clickY[x],clickX[i]-clickX[x],clickY[i]-clickY[x]);


          }

         // context.lineTo(clickX[i], clickY[i]);
         context.fillStyle=this.color;
          context.stroke();
      }
    }
shapeslist=new Array()
canvasships=new Array()

cr=new Shape();
li=new Shape();
tr=new Shape();
re=new Shape();
el=new Shape();
shape=new Path2D;

 Draw(shape:String){

switch(shape){
     case "circle":
      this.putattr(this.cr,'circle',this.startx,this.starty,this.color,false,true)
      this.circle.strokeStyle=this.color;
      this.shape.arc(this.startx,this.starty,50,0,Math.PI*2)
      this.circle.stroke(this.shape);
      this.shapeslist.push(this.cr);
      this.canvasships.push(this.circle)
     break;
     case "elipse":
      this.putattr(this.el,'elipse',this.startx,this.starty,this.color,false,true)

      this.elipse.fillStyle=this.color;
      this.shape.ellipse(this.startx,this.starty,50,70,0,0,Math.PI*2)
     this.elipse.stroke(this.shape);
     this.shapeslist.push(this.el);
      this.canvasships.push(this.elipse)
     break;
   case "rectangle":
    this.putattr(this.re,'rectangle',this.startx,this.starty,this.color,false,true)

    this.rectangle.fillStyle=this.color;
    this.shape.rect(this.startx,this.starty,50,60)
    this.rectangle.stroke(this.shape);
    this.shapeslist.push(this.re);
    this.canvasships.push(this.rectangle)
   break;

   case "line":
    this.putattr(this.li,'line',this.startx,this.starty,this.color,false,true)

    this.line.fillStyle=this.color;
     this.shape.moveTo(this.startx,this.starty)
     this.shape.lineTo(100,200);
     this.line.stroke(this.shape);
     this.shapeslist.push(this.li);
     this.canvasships.push(this.line)
      break;


   case "triangle":
     this.putattr(this.tr,'triangle',this.startx,this.starty,this.color,false,true)
     this.triangle.strokeStyle = this.color;
     this.shape.moveTo(this.startx, this.starty);
     this.shape.lineTo(100, 150);
    this.shape.lineTo(150,100);
    this.shape.closePath()
     this.triangle.stroke(this.shape);
     this.shapeslist.push(this.tr);
    this.canvasships.push(this.triangle)
     break;




}


}

putattr(sh:Shape,name:string,x:Number,y:Number,color:string,sel:boolean,draw:boolean){
  sh.setname(name)
  sh.setcolor(color)
  sh.setcx(x) ;
  sh.setcy(y);
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





