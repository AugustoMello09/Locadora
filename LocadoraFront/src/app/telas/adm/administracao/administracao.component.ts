import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-administracao',
  templateUrl: './administracao.component.html',
  styleUrls: ['./administracao.component.css']
})
export class AdministracaoComponent implements OnInit {

  userPaged:[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.users();
  }

  public users() {
    this.userService.findAllPaged(0, 10).subscribe( 
     res => {
      this.userPaged = res.content;
      }, err => {
        console.log(err)
      }
    )
  }

}
