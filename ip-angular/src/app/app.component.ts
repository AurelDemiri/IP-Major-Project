import { Component, OnInit } from '@angular/core';
import { RestService } from './rest.service';
import { DagMenu } from './dagmenu.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'WeekMenu Overzicht';
  dagmenus$: DagMenu[];

  constructor(private restService: RestService) { }
  
  updateTable() {
    return this.restService.getWeekMenu()
    .subscribe(dagmenus => this.dagmenus$ = dagmenus);
  }

  ngOnInit() {
    this.updateTable();
  }

  onJaarChange(jaar: string) {
    this.restService.jaar = jaar;
    this.updateTable();
  }
  
  onWeeknummerChange(weeknummer: string) {
    this.restService.weeknummer = weeknummer;
    this.updateTable();
  }

  numberArray(startfrom: number, n: number): any[] {
    return [...Array(n).keys()].map(i => i + startfrom);
  }
}
