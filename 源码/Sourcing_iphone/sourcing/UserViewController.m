//
//  UserViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-4.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "UserViewController.h"
#import "CustomNavigationBar.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface UserViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation UserViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserViewController
{
    [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"icon_home_tb_user"] withFinishedUnselectedImage:[UIImage imageNamed:@"icon_home_tb_user"]];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitUserViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitUserViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
}

- (void)viewDidUnload {
	// your code here
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods


@end
