import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservaOnline } from 'src/app/model/reservaOnline.model';
import { AuthService } from 'src/app/service/auth.service';
import { ReservaService } from 'src/app/service/reserva.service';

@Component({
  selector: 'app-reservainfo',
  templateUrl: './reservainfo.component.html',
  styleUrls: ['./reservainfo.component.css']
})
export class ReservainfoComponent implements OnInit {

  id_reserva = '';

  reserva: ReservaOnline = {
    qtdReservada: '',
    dataReserva: '',
    statusReserva: ''
  }

  isAdmin: boolean = false;

  constructor(private router: ActivatedRoute,
    private route: Router, private reservaService: ReservaService, private auth: AuthService) { }

  ngOnInit(): void {
    const userRoles = this.auth.hasAdminRole();
    this.isAdmin = userRoles.includes('ROLE_ADMIN');
    this.id_reserva = this.router.snapshot.paramMap.get('id')!;
    this.findById();
  }
  
  findById(): void {
    this.reservaService.findById(this.id_reserva).subscribe(res => {
      this.reserva = res;
    })
  }

  cancelar(): void {
    this.reservaService.cancelar(this.id_reserva).subscribe(() => {
      this.route.navigate(['/reservas'])
    })
  }

  voltar(): void {
    if (this.isAdmin) {
      this.route.navigate(['/reservasAdm'])
    } else {
      this.route.navigate(['/reservas'])
    }
  }

}
