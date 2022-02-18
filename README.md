# kpass__client :peach:
#### Application for keeping logins and passwords.

## Getting started :fearful:

> Please install java before using this application

#### 1. Create the following folder
```
$ mkdir ~/Binary
```
#### 2. Put the following line in `~/.bashrc` file
```bash
export USR_BIN=~/Binary
```
#### 3. Clone this repository somewhere. I will do this in the `~/Coding` folder
```
$ cd ~/Coding
$ git clone https://github.com/ZERDICORP/kpass__client.git
```
#### 4. Change to `src` directory and run `build` script
```
$ cd ~/Coding/kpass__client/src && ./build
```
#### 5. Go to the `build` directory and run `mkbin` script
```
$ cd ~/Coding/kpass__client/build && ./mkbin
```
#### 6. Finnaly, you can use the `kpass` command everywhere
```
$ kpass
```
#### ~ 7. Now you can remove everything except `build` folder
```
$ rm -rf ~/Coding/kpass__client/!(build)
```
## Usage help :cat2:

#### Log into account
```
$ kpass auth <email> <password>
```
#### Create an account
```
$ kpass auth --new <email> <password>
```
#### Log out
```
$ kpass logout
```
#### Get services
```
$ kpass f=<name>
```
> Or, to get all services you can use the following command
```
$ kpass f=*
```
#### Add service
```
$ kpass a <name> <login> <password>
```
> You can also generate a random password
```
$ kpass a <name> <login> gen
```
#### Edit service
```
$ kpass e <index> <new_name> <new_login> <new_password>
```
> If you do not want to change a certain field, you can put "_" in its place, then its value will remain the same.  
> In the example below, I chose not to change the name.
```
$ kpass e 83 _ my@gmail.com Qwerty123
```
> And, also, you can generate a password automatically
```
$ kpass e 83 _ my@gmail.com gen
```
#### Delete service
```
$ kpass d <index>
```
#### Change account password
```
$ kpass pwd <new_password>
```
