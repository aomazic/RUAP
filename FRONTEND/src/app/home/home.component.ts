import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { InputService } from "../input.service";
import { Input } from "../Input";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  inputForm = new FormGroup({
    Maker: new FormControl(''),
    Model: new FormControl(''),
    Mileage: new FormControl(0),
    fuel: new FormControl(''),
    gear: new FormControl(''),
    offerType: new FormControl(''),
    Hp: new FormControl(0),
    year: new FormControl(0)
  });

  predictions: {input: Input, response: string}[] = [];
  displayedColumns: string[] = ['make', 'mileage', 'model', 'fuel', 'gear', 'offerType', 'hp', 'year', 'predicted price'];
  constructor(private inputService: InputService, private snackBar: MatSnackBar) { }

  getMl() {
    const input: Input = {
      make: this.inputForm.get('Maker')?.value || '',
      mileage: this.inputForm.get('Mileage')?.value || 0,
      model: this.inputForm.get('Model')?.value || '',
      fuel: this.inputForm.get('fuel')?.value || '',
      gear: this.inputForm.get('gear')?.value || '',
      offerType: this.inputForm.get('offerType')?.value || '',
      hp: this.inputForm.get('Hp')?.value || 0,
      year: this.inputForm.get('year')?.value || 0,
    };

    this.inputService.getMLResponse(input).subscribe({
      next: (response: string) => {
        const prediction = { input, response };
        this.predictions = [...this.predictions, prediction];
        this.openSnackBar(response);
        console.log(prediction);
        this.openSnackBar(response);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  openSnackBar(response: string) {
    this.snackBar.open("Predicted price: "+response + " €", 'Close', {
      duration: 5000,
    });
  }
}
