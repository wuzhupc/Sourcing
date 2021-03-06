//
//  UserLoginViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-15.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <UIKit/UIKit.h>
#import "RadiusView.h"
#import "BaseServiceDelegate.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

#define CINT_TAG_USERLOGIN 10015

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface UserLoginViewController : UIViewController<UITextFieldDelegate,BaseServiceDelegate>


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property (weak, nonatomic) IBOutlet UITextField *textFieldPwd;
@property (weak, nonatomic) IBOutlet UILabel *labelTitle;
@property (weak, nonatomic) IBOutlet RadiusView *viewInput;
@property (weak, nonatomic) IBOutlet UITextField *textFieldUserName;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Outlets

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
- (IBAction)actionReturn:(id)sender;
- (IBAction)actionRegister:(id)sender;
- (IBAction)actionLogin:(id)sender;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions


@end
