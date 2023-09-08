import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'primeng/api';
import { Role } from 'src/app/model/role.model';
import { UserU } from 'src/app/model/userUtil.model';
import { RoleService } from 'src/app/service/role.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-userup',
  templateUrl: './userup.component.html',
  styleUrls: ['./userup.component.css']
})
export class UserupComponent implements OnInit {

  id_user = '';

  message1: Message[] = [];

  roles: Role[] = [];

  user: UserU = {
    id: '',
    name: '',
    email: '',
    roles: [
      { id: '' } 
    ]
  }
  
  constructor(private roleService: RoleService, private userService: UserService, private router: ActivatedRoute,
    private route: Router) { }

  ngOnInit(): void {
    this.id_user = this.router.snapshot.paramMap.get('id')!;
    this.findById();
    this.cargos();
  }

  cargos() {
    this.roleService.findAll().subscribe(data => {
      this.roles = data;
    })
  }

  updateCargo() {
    this.userService.cargo(this.id_user, this.user).subscribe(data => {
      this.user = data;
      this.route.navigate(['/usuarios']);
    })
  }

  findById() {
    this.userService.findById(this.id_user).subscribe(data => {
      this.user = data;
    })
  }

}
