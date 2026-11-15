# Tenant Management System

An Android application for managing tenant records and rent information.

## Features

- Register and sign in to an account.
- View the tenant count and total rent on the dashboard.
- Add tenants and view saved tenant records.
- Open the phone dialer with a tenant's number ready to call.
- Keep tenant records on the device using Room.

## Architecture

The app separates screen code from data and storage code:

- **`ui`** — Activities, Fragments, screens, and ViewModels.
- **`data`** — Models, repositories, Room database, and DAOs.
- **Room** stores tenant records locally.
- **ViewModels** prepare screen data and expose it to the UI.

## Team

| Member | Admission number | Contribution / PR |
|---|---:|---|
| Linda Nyika | 191329 | [Organise code into ui and data packages](https://github.com/linnyika/MobileApplication-Assignment/pull/1) |
| Abasa Emmanuel | 131805 | Stage 6 release work — PR pending |
| Joy Gitau | 193344 | [Stage 2: TenantViewModel](https://github.com/linnyika/MobileApplication-Assignment/pull/2) |
| Grace Waithera | 190433 | [features/screens](https://github.com/linnyika/MobileApplication-Assignment/pull/4) |
| Tracy Karuga | 172066 | [Stage 5: Integrate Room Database](https://github.com/linnyika/MobileApplication-Assignment/pull/5) |
| Derrick Mangeli | 191733 | [Stage 3](https://github.com/linnyika/MobileApplication-Assignment/pull/3) |

## Release

Version: **1.0**

Download the APK from the [GitHub Releases page](https://github.com/linnyika/MobileApplication-Assignment/releases) after the v1.0 release is published.