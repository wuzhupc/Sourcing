//
//  NewsViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-2.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "NewsViewController.h"
#import "CustomNavigationBar.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface NewsViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation NewsViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitNewsViewController
{
    [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"icon_home_tb_news"] withFinishedUnselectedImage:[UIImage imageNamed:@"icon_home_tb_news"]];
    //self.tabBarController.tabBar.tintColor = [UIColor clearColor];
    //[self.tabBarController.tabBar setBackgroundImage:[UIImage imageNamed:@"tabbar_backgroundimage"]];
    //self.tabBarController.tabBar.selectionIndicatorImage = [UIImage imageNamed:@"tabbar_selectionindicatorimage"];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitNewsViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitNewsViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
}

- (void)viewDidUnload {
	// your code here
    [self setCustomNavigationBar:nil];
    [self setTitleNavigationItem:nil];
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
