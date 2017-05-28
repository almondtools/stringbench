import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { NvD3Module } from 'angular2-nvd3';

import { Chart } from './app.component';
import { Sample } from './app.core';

@NgModule( {
    declarations: [
        Chart
    ],
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        HttpModule,
        NvD3Module
    ],
    bootstrap: [Chart]
} )
export class AppModule { }
