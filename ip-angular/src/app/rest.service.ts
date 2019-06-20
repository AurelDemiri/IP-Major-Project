import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DagMenu} from './dagmenu.model';

@Injectable({
  providedIn: 'root'
})
export class RestService {
  jaar = '2019';
  weeknummer = '22';
  apiUrl = 'http://193.191.177.8:10977/weekmenu/';

  constructor(private _http: HttpClient) { }

  getWeekMenu() {
    return this._http.get<DagMenu[]>(this.apiUrl + this.jaar + '/' + this.weeknummer);
  }
}
