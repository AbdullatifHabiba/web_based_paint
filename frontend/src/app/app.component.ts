import { Component, ElementRef, ViewChild,OnInit ,HostListener,Directive} from '@angular/core';
import { HttpClient ,HttpErrorResponse,HttpParams} from '@angular/common/http';
    import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',

  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit{
  title = 'webpaint';
  constructor(private httpClient:HttpClient){}

  @ViewChild('canvas',{static:true})
  mycanvas!: ElementRef<HTMLCanvasElement>;
  public ctx!: CanvasRenderingContext2D;

  colors='';
   shape=new Path2D;

ngOnInit(): void {
  this.ctx=<CanvasRenderingContext2D>this.mycanvas.nativeElement.getContext("2d");

}
curspoints:Array<number>=[]



@HostListener('drag')
getdownMousePos(mycanvas: any,event:any) {
  var rect = this.mycanvas.nativeElement.getBoundingClientRect();


  return {
    x: event.clientX - rect.left,
    y: event.clientY - rect.top
  };
}

Select(){

}


 Draw(shape:String){
  var startx=0;
  var starty=0;
  // @HostListener('mousedown')
  // const change = () =>{
  //   startx=this.getdownMousePos(this.mycanvas,event).x;
  //   starty=this.getdownMousePos(this.mycanvas,event).y;
  // }


this.ctx.fillStyle=this.colors;

console.log(startx,starty)
switch(shape){
     case "circle":


      this.shape.arc(startx,starty,50,0,Math.PI*2)
     this.ctx.stroke(this.shape);
     break;
     case "elipse":

      this.ctx.fillStyle=this.colors;

      this.shape.ellipse(startx,starty,50,70,0,0,Math.PI*2)

     this.ctx.stroke(this.shape);
     break;
   case "rectangle":
    startx=this.getdownMousePos(this.mycanvas,event).x;
    starty=this.getdownMousePos(this.mycanvas,event).y;
    this.ctx.fillStyle=this.colors;

this.shape.rect(startx,starty,50,60)
this.ctx.stroke(this.shape);
   break;

   case "line":
    startx=this.getdownMousePos(this.mycanvas,event).x;
    starty=this.getdownMousePos(this.mycanvas,event).y;
    this.ctx.fillStyle=this.colors;

     this.shape.moveTo(startx,starty)
     this.shape.lineTo(100,200);
   this.ctx.stroke(this.shape);

      break;


   case "triangle":
     this.ctx.strokeStyle = this.colors;
     this.shape.moveTo(100, 100);
     this.shape.lineTo(100, 150);
    this.shape.lineTo(150,100);
    this.shape.closePath()
     this.ctx.stroke(this.shape);
     break;




}


}

//Shapes:Array<Shape>;
  shapeattributes:Array<string>= [
     '100', '100',"50" , "blue",'true'
  ];

results:any;
send(){
  const serverUrl = environment.serverUrl;
  let params = new HttpParams();
  for (let item of this.shapeattributes) {
    params = params.append('sentobj', item);
  }
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



