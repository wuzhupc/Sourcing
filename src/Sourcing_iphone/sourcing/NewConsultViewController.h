//
//  NewConsultViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-20.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <UIKit/UIKit.h>
#import "BaseServiceDelegate.h"
#import "RadiusView.h"
#import "UIPlaceHolderTextView.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants
#define CINT_TAG_SENDCONSULT 10020

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface NewConsultViewController : UIViewController<UITextViewDelegate,BaseServiceDelegate>


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property (weak, nonatomic) IBOutlet RadiusView *viewConsult;
@property (weak, nonatomic) IBOutlet UIPlaceHolderTextView *textViewConsult;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Outlets

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions
- (IBAction)actionReturn:(id)sender;

- (IBAction)actionSendConsult:(id)sender;

@end
