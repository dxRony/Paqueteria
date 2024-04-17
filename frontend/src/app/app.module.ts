import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { InicioSesionComponent } from "./components/inicio-sesion/inicio-sesion.component";
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';

@NgModule({
    declarations: [
        AppComponent
    ],
    providers: [
        provideClientHydration(),
        provideHttpClient(withFetch())
    ],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        InicioSesionComponent,
        HttpClientModule
    ]
})
export class AppModule { }
